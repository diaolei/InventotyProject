package www.zhaoxinkeji.com.inventotyproject.http.imageloader.config;


import www.zhaoxinkeji.com.inventotyproject.http.imageloader.glide.GlideCircleTransform;
import www.zhaoxinkeji.com.inventotyproject.http.imageloader.glide.ImageConfigImpl;

/**
 * <pre>
 *     author : wanlinruo
 *     time   : 2017/12/27
 *     desc   : 简化店铺头像配置
 *     version: 1.0
 * </pre>
 */

public class StoreFaceConfigImpl {

    public static ImageConfigImpl.Builder builder() {
        return ImageConfigImpl.builder()
                //                .placeholder(R.mipmap.ic_user_default)
                //                .errorPic(R.mipmap.ic_user_default)
                //                .fallback(R.mipmap.ic_user_default)
                .cacheStrategy(0)
                .transformation(new GlideCircleTransform());
    }
}
