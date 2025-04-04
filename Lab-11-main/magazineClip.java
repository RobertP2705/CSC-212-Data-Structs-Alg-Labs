
import java.util.HashMap;
public class magazineClip {
    public static boolean willItClip(String note, String[] articles){
        String compressArticle = "";
        for(int i=0; i< articles.length;i++){
            compressArticle += articles[i];
        }
        HashMap<Character,Integer> hash = new HashMap<>();
        for(int i=0; i<compressArticle.length(); i++){
            if(compressArticle.charAt(i) == ' '){continue;}
            if(hash.get(compressArticle.charAt(i)) == null)
            {
                hash.put(compressArticle.charAt(i),1);
            }
            else
            {
                hash.put(compressArticle.charAt(i),hash.get(compressArticle.charAt(i))+1);
            }
        }
        for(int i=0; i<note.length();i++){
            if(note.charAt(i) == ' '){continue;}
            if(hash.get(note.charAt(i))==null)
            {
                return false;
            }
            else
            {
                hash.put(note.charAt(i),hash.get(note.charAt(i))-1);
                if(hash.get(note.charAt(i))<0){return false;}
            }
        }
        return true;
    }
    public static void main(String[] args) {
        String note = "aba  a";
        String[] articles = {"ba","chickaena"};
        
        String note2 = "I eat potatoes";
        String[] articles2 = {"eata","potat"};

        System.out.println(willItClip(note,articles));
        System.out.println(willItClip(note2,articles2));

    }
}