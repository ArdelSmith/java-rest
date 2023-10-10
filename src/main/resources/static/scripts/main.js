Vue.component('purchase-row', {
    props: ['purchase', 'purchases'],
               template: `
                                 <div>
                                     <span>{{ purchase.text }}</span>
                                     <span style="position: absolute; right: 0">
                                         <span class="status" >{{ status }}</span>
                                         <input type="button" :value="action" @click="edit"/>
                                         <input type="button" value="X" @click="del" />
                                     </span>
                                 </div>
                             `,
    computed: {
        status() {
            if (this.purchase.status) return 'Куплено'
            else return 'Не куплено';
        },
        action() {
            if (this.purchase.status) return 'Отметить'
            else return 'Снять отметку';
        }
    },
    methods: {
        del: function() {
                    purchases.remove({id: this.purchase.id}).then(result => {
                        if (result.ok) {
                            this.purchases.splice(this.purchases.indexOf(this.purchase), 1)
                        }
                    })
                },
        edit: function(){
            this.editMethod(this.purchase);
        }
    }

});




Vue.component('purchase-form',{
    props: ['purchases', 'purchaseAttr'],
    data: function(){
        return {
            text: ''
        }
    },
    watch: {
            purchaseAttr: function(newVal, oldVal) {
                this.text = newVal.text;
                this.id = newVal.id;
            }
        },
    template:
        `<div>
                <input type="text" placeholder="Хочу купить..." v-model="text" />
                <input type="button" value="Сохранить" @click="save" />
        </div>`,
    methods:{
        save: function(){
            this.$http.post('/list', null, {
                params: {
                    text: this.text
                }
            }).then(response => {
                              if (response.ok) {
                                  this.purchases.push(response.body);
                              }
           });
        }
    }
});

var app = new Vue({
            el: '#app',
            template:`
                <div>
                    <purchase-form :purchases="purchases" :purchaseAttr="purchase" />
                    <purchase-row v-for="purchase in purchases" :key="purchase.id" :purchase="purchase"
                    :purchases="purchases" />
                </div>
               `,
            data: {
                purchases: []
            },
            mounted: function() {
                this.loadMessages();
            },
            methods: {
                loadMessages: function() {
                    this.$http.get('/list').then(function(response) {
                        this.purchases = response.body;
                    });
                }
            }
        });