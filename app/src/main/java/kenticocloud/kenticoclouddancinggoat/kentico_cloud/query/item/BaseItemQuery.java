package kenticocloud.kenticoclouddancinggoat.kentico_cloud.query.item;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import kenticocloud.kenticoclouddancinggoat.kentico_cloud.config.DeliveryClientConfig;
import kenticocloud.kenticoclouddancinggoat.kentico_cloud.interfaces.item.common.IQueryParameter;
import kenticocloud.kenticoclouddancinggoat.kentico_cloud.interfaces.item.item.IContentItem;
import kenticocloud.kenticoclouddancinggoat.kentico_cloud.query.BaseQuery;
import kenticocloud.kenticoclouddancinggoat.kentico_cloud.services.QueryService;
import kenticocloud.kenticoclouddancinggoat.kentico_cloud.services.map.ResponseMapService;

public abstract class BaseItemQuery<T extends IContentItem> extends BaseQuery {

    protected BaseItemQuery(@NonNull DeliveryClientConfig config){
        super(config);
    }
}
