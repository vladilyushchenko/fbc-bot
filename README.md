# @fpm_blya_bot

---
@fpm_blya_bot is a bot for fun messaging in chats

### Functional description

---
1. Inline query (write in any chat "@fpm_blya_bot [...]" to see an option):\
![alt text](https://i.ibb.co/VvtYxXG/cock-size-inline-query.png)
2. Live chatting with bot in groups or bot's direct chat:\
![alt_text](https://i.ibb.co/3W1Ggx3/chat-cock-size.png)

### Install instruction

---
When starting an application set -Djava.net.useSystemProxies=true
for JVM to setup proxy for application if
Telegram Api is unavailable in your country.

<b>Use existing docker-compose.yml file to run the server via terminal command</b>

    docker-compose up

<b>Bot works via webhook technology. You should set webhook link via following link:</b>

    https://api.telegram.org/{bot_token}}/setWebhook?url={webhook-link}

You could get current state of bot via following link:

    https://api.telegram.org/{bot_token}/getWebhookInfo