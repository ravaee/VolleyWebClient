# VoleyWebclient
Voley Webclient help you to fetch data from restful webservices in android native apps 

## Usages
you need just make new object and override 2 listeners (CallBackMethods):

```java
      @Override
      public void onSuccessDataReceived(Object response) {
          //Do what you want to do with your response
      }

      @Override
      public void onErrorDataReceived(Object response) {
          //show Error 
      }
```

```java

    private void initialProviderList(){

        String url = ProjectSettings.apiUrl+"provider/getAll";
        WebClient webClient = new WebClient(ProvidersActivity.this);
        webClient.getData(url,null);
        webClient.setDataReceivedListener(new WebClient.OnDataReceivedListener() {

            @Override
            public void onSuccessDataReceived(Object response) {
                //Do what you want to do with your response
            }

            @Override
            public void onErrorDataReceived(Object response) {
                //show Error 
            }
        });
    }

```
