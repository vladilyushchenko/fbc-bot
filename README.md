# @fpm_blya_bot
@fpm_blya_bot is a bot for fun messaging in chats
### Functional description:
1. Inline query (write in any chat "@fpm_blya_bot [...]" to option):
![alt text](https://i.ibb.co/VvtYxXG/cock-size-inline-query.png)
2. Live chatting with bot in group or bot's direct chat:
![alt_text](https://i.ibb.co/3W1Ggx3/chat-cock-size.png)

###Setup instruction:
When starting an application set -Djava.net.useSystemProxies=true
for JVM to setup proxy for application if
Telegram Api is unavailable in your country.

Bot works via webhook technology. You should set webhook link via following link:

    https://api.telegram.org/bot2102122174:AAEvhcaJAQ-cfThHhcfE6pbkKkwyFW9sVyc/setWebhook?url={webhook-link}

You could get current state of bot via following link:

    https://api.telegram.org/{bot_token}/getWebhookInfo