public class TreeMap {
    private SplayTree<KeyValue> tree;
    public TreeMap(){
        tree = new SplayTree<>();
    }

    public void insert(String key, String value){
        KeyValue newval = new KeyValue(key, value);
        tree.insert(newval);
    }

    public String get(String key){
        KeyValue searchval = new KeyValue(key, "");
        KeyValue searched = tree.get(searchval);
        if(searched !=null){
            return searched.value;
        }
        else return null;
        
    }

    public String delete(String key){
        KeyValue deleteval = new KeyValue(key, "");
        KeyValue searched = tree.remove(deleteval);
        if(searched !=null){
            return searched.value;
        }
        else return null;
    }
}
