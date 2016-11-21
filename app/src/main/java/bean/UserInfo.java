package bean;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by zhaoziliang on 16/10/18.
 */

public class UserInfo implements Serializable{
    public String userId;
    public String headshotUrl;
    public String content;
    public ArrayList<String> content_imgs;
    public boolean isShowAll = false;
<<<<<<< HEAD
    public DynamicInfo dynamicInfo;
=======
>>>>>>> cdfcdfc46b6e5ef6f5ecad79807a164d7f0434e0
}
