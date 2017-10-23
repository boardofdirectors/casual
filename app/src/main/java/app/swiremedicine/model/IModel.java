package app.swiremedicine.model;

import io.reactivex.Observable;
import okhttp3.ResponseBody;

/**
 * Created by 王坤 on 2017/10/23.
 */

public interface IModel {
    Observable<ResponseBody> register();
}
