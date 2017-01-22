package com.languagehelper.http.task;


import com.common.core.internet.Api;
import com.common.core.internet.ApiCallback;
import com.common.core.internet.ApiModelList;

/**
 * 收藏相关API
 *
 * @author kevin
 * @version v1.0
 * @since 2014-10/6/14
 */
public class FavoriteApi extends Api {

    public static final String TYPE_RECIPE = "recipe";
    public static final String TYPE_TOPIC = "topic";
    public static final String TYPE_PARTY = "events";

    /**
     * 各类我的收藏列表
     *
     * @param type
     * @param page
     * @param pageSize
     * @param callback
     */
    public static  void favoriteList(ApiCallback<ApiModelList> callback ) {
        Api.get(FavoriteApi.class)
                .setApiCallback(callback)
                .executeGet();
    }



}
