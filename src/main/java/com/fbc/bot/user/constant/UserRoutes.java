package com.fbc.bot.user.constant;

import static com.fbc.bot.common.constant.BaseRoutes.*;

public interface UserRoutes {

    String USERS = BASE_API + URI_SEPARATOR + "users";

    String USER = USERS + URI_SEPARATOR + ID_PATH_VARIABLE;
}