public class KeyValue implements Comparable<KeyValue>{
    public String key;
    public String value;
    public Object priority;
    public KeyValue(String k, String v){
        key = k;
        value = v;
    }

    @Override
    public int compareTo(KeyValue key2){
        return this.key.compareTo(key2.key);
    }

    @Override
    public String toString() {
        return key+": "+value;
    }
}
