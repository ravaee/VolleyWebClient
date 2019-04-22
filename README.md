# VoleyWebclient
Voley Webclient help you to fetch data from restful webservices in android native apps 

# Usages
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

## Http Get example

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
## Http Post example
another exmaple for post request to get token in OAuth2 authontication system.

```java
    private void sendGetTokenRequest(){
        WebClient webClient = new WebClient(CodeActivity.this);
        String url=ProjectSettings.apiUrlGetToken;

        Map<String,String> getTokenParams = new HashMap<>();
        getTokenParams.put("username","mohamad");
        getTokenParams.put("password","asd123123");
        getTokenParams.put("grant_type","password";

        webClient.postData(url,getTokenParams,null);

        webClient.setDataReceivedListener(new WebClient.OnDataReceivedListener() {
            @Override
            public void onSuccessDataReceived(Object response) {

                try {
                  JSONObject jsonObject = new JSONObject(response.toString());
                  Toast.makeText(CodeActivity.this, jsonObject.getString("access_token"), Toast.LENGTH_SHORT).show();
                  Setting.getInstance(CodeActivity.this).storeBearerToken(jsonObject.getString("access_token"));
                }
                } catch (JSONException e) {
                  Toast.makeText(CodeActivity.this, "get token failed", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onErrorDataReceived(Object response) {
                Toast.makeText(CodeActivity.this, "there was a problem in connection to server", Toast.LENGTH_SHORT).show();
            }
        });
    }

```
