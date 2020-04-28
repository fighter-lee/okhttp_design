# OKHTTP源码解析

> 基于okhttp 3

## 1. 使用

[document](https://square.github.io/okhttp/)

异步调用

```java
OkHttpClient client = new OkHttpClient();
Request request = new Request.Builder()
        .url(url)
        .build();

client.newCall(request).enqueue(new Callback() {
    @Override
    public void onFailure(Call call, IOException e) {

    }

    @Override
    public void onResponse(Call call, Response response) throws IOException {

    }
});
```

同步调用

```java
Response response = client.newCall(request).execute();
```



## 2. 源码学习

### 2.1 整体调用流程

![](.\doc\jpg\okhttp 时序图.jpg)

#### 2.2 封装请求

1. Http协议报文封装在Request中；
2. 真正的请求封装在Call/RealCall中，RealCall对象持有OkHttpClient对象、Request对象以及Transmitter对象；

#### 2.3 发送请求

1. 同步请求

   ```
   getResponseWithInterceptorChain()->response
   ```

2. 异步请求

   ```
   AsyncCall填入调度器中->execute()->getResponseWithInterceptorChain()->callback
   ```



### 3.2 调度器

​	Dispatcher分析

### 3.3 拦截器

https://www.jianshu.com/p/8d6eb5efc36c

![](.\doc\jpg\拦截器.jpg)

1. 责任链模式

   