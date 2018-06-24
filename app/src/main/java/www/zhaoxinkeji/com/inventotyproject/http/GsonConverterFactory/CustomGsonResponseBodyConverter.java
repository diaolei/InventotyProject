package www.zhaoxinkeji.com.inventotyproject.http.GsonConverterFactory;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;

import okhttp3.MediaType;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import www.zhaoxinkeji.com.inventotyproject.http.ApiException;
import www.zhaoxinkeji.com.inventotyproject.model.entity.JavaResponse;

import static okhttp3.internal.Util.UTF_8;

/**
 * <pre>
 *     author : mayn
 *     time   : 2018/03/28
 *     desc   :
 *     version: 1.0
 * </pre>
 */

final class CustomGsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {
    private final Gson gson;
    private final TypeAdapter<T> adapter;

    CustomGsonResponseBodyConverter(Gson gson, TypeAdapter<T> adapter) {
        this.gson = gson;
        this.adapter = adapter;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
        String response = value.string();
        //String replace = response.replace("(", "").replace(")", "");
        JavaResponse javaResponse = gson.fromJson(response, JavaResponse.class);
        if (!javaResponse.isSuccess()) {
            value.close();
            throw new ApiException(javaResponse.getErrcode(), javaResponse.getErrmsg());
        }

        MediaType contentType = value.contentType();
        Charset charset = contentType != null ? contentType.charset(UTF_8) : UTF_8;
        InputStream inputStream = new ByteArrayInputStream(response.getBytes());
        Reader reader = new InputStreamReader(inputStream, charset);
        JsonReader jsonReader = gson.newJsonReader(reader);

        try {
            return adapter.read(jsonReader);
        } finally {
            value.close();
        }
    }
}
