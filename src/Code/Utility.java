package Code;

abstract public class Utility {

    public static String polishString(String S){
        String[] t =S.split(" ");
        S="";
        for(String tmp:t){
            if(tmp.isEmpty())
                continue;
            S=S+tmp.substring(0,1).toUpperCase()+tmp.substring(1).toLowerCase()+" ";
        }
        return S.trim();
    }

    public static boolean checkPos(String ss) {
        return ss.equalsIgnoreCase("Forward") || ss.equalsIgnoreCase("Defender") || ss.equalsIgnoreCase("Midfielder") || ss.equalsIgnoreCase("Goalkeeper");
    }

    public static boolean isInt(String s){
        try{
            if(Integer.parseInt(s)>0)
                return true;
            }
        catch(Exception e){
            return false;
        }
        return false;
    }

    public static boolean isDouble(String s){
        try{
            if(Double.parseDouble(s)>0)
                return true;
        }
        catch(Exception e){
            return false;
        }
        return false;
    }

    public static boolean isNameType(String s)
    {
        if (s == null) {
            return false;
        }

        for (int i = 0; i < s.length(); i++)
        {
            char c = s.charAt(i);
            if (!(c >= 'A' && c <= 'Z') && !(c >= 'a' && c <= 'z') && !(c==' ') &&!(c=='.')){
                return false;
            }
        }
        return true;
    }

}
