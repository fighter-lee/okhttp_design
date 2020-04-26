# OKHTTP源码解析

> 基于okhttp 3

## 1. 使用

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



## 2. 整体流程

![](.\doc\jpg\okhttp 时序图.jpg)

## 3. 源码学习

### 3.1 整体调用流程

1. https://www.jianshu.com/p/d98be38a6d3f
2. 

### 3.2 调度器



### 3.3 拦截器