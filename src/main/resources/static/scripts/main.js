Vue.component('message-form',{
    props: ['messages', 'messageAttr'],
    data: function(){
        return {
            text: ''
        }
    },
    watch: {
            messageAttr: function(newVal, oldVal) {
                this.text = newVal.text;
                this.id = newVal.id;
            }
        },
    template:
        `<div>
                <input type="text" placeholder="Write something" v-model="text" />
                <input type="button" value="Save" @click="save" />
        </div>`,
    methods:{
        save: function(){
            this.$http.post('/list', null, {
                params: {
                    text: this.text
                }
            });
        }
    }
});

var app = new Vue({
            el: '#app',
            template:`
                <div>
                    <message-form :messages="messages" :messageAttr="message" />
                    <li v-for="message in messages" :key="message.id">
                                          {{ message.text }} - {{ message.status }}
                    </li>
                </div>
               `,
            data: {
                messages: []
            },
            mounted: function() {
                this.loadMessages();
            },
            methods: {
                loadMessages: function() {
                    this.$http.get('/list').then(function(response) {
                        this.messages = response.body;
                    });
                }
            }
        });