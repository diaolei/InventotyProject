package www.zhaoxinkeji.com.inventotyproject.base;


import www.zhaoxinkeji.com.inventotyproject.http.RetrofitFactory;

public class BaseModel implements IModel {

    protected RetrofitFactory mRetrofitFactory;

    public BaseModel() {
        mRetrofitFactory = RetrofitFactory.getInstance();
    }

}
