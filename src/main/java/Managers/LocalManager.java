package main.java.Managers;


public class LocalManager {

    private static ThreadLocal<ApiManager> apiManager = new ThreadLocal<>();

    public static ApiManager getApi() {
        if (apiManager.get() != null) {
            return apiManager.get();
        } else {
            ApiManager api_manager = new ApiManager();
            setApiManager(api_manager);
            return apiManager.get();
        }

    }


    public static void setApiManager(ApiManager api_Manager) {
        apiManager.set(api_Manager);
    }

    public static void removeApiDriver() {
        apiManager.remove();
    }
}

