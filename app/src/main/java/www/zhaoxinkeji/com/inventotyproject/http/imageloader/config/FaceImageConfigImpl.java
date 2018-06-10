package www.zhaoxinkeji.com.inventotyproject.http.imageloader.config;


import www.zhaoxinkeji.com.inventotyproject.http.imageloader.glide.GlideCircleTransform;
import www.zhaoxinkeji.com.inventotyproject.http.imageloader.glide.ImageConfigImpl;

/**
 * 简化头像配置
 */
public class FaceImageConfigImpl {

    public static ImageConfigImpl.Builder builder() {
        return ImageConfigImpl.builder()
//                .placeholder(R.mipmap.ic_user_default)
//                .errorPic(R.mipmap.ic_user_default)
//                .fallback(R.mipmap.ic_user_default)
                .cacheStrategy(0)
                .transformation(new GlideCircleTransform());
    }

}
