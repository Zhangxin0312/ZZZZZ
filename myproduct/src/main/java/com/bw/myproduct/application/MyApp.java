package com.bw.myproduct.application;

import android.app.Application;
import android.os.Environment;

import com.facebook.cache.disk.DiskCacheConfig;
import com.facebook.common.internal.Supplier;
import com.facebook.common.util.ByteConstants;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.cache.MemoryCacheParams;
import com.facebook.imagepipeline.core.ImagePipelineConfig;

import java.io.File;


public class MyApp extends Application {
    private  static  int  Max_Men=30*ByteConstants.MB;
    @Override
    public void onCreate() {
        super.onCreate();
        //缓存SD卡位置
        String path=Environment.getExternalStorageDirectory()+"/myProduct";
        //Fresco缓存
        DiskCacheConfig diskCacheConfig = DiskCacheConfig.newBuilder(this)
                .setBaseDirectoryName("image")
                .setBaseDirectoryPath(new File(path))
                .build();
        //缓存大小
        final MemoryCacheParams memoryCacheParams=new MemoryCacheParams(Max_Men,Integer.MAX_VALUE,Max_Men,Max_Men,Max_Men);
        //存入类中
        Supplier<MemoryCacheParams> supplier=new Supplier<MemoryCacheParams>() {
            @Override
            public MemoryCacheParams get() {
                return memoryCacheParams;
            }
        };
        //缓存
        ImagePipelineConfig.Builder builder = ImagePipelineConfig.newBuilder(this);
        builder.setEncodedMemoryCacheParamsSupplier(supplier);
        builder.setMainDiskCacheConfig(diskCacheConfig);
        Fresco.initialize(this,builder.build());
    }
}
