package ModuleLesson9;


public class EncryptAndDecryptTheText {

    public String encrypt(String text){
        char[] enText = workWithString(text);

        for(int i = 0; i<enText.length; i++){
            enText[i] = (char) ((int) enText[i] + 3);
        }
        return String.valueOf(enText);
    }
    public String decrypt(String text){
        char[] enText = workWithString(text);

        for(int i = 0; i<enText.length; i++){
            enText[i] = (char) ((int) enText[i] - 3);
        }

        return String.valueOf(enText);
    }

    private char[] workWithString(String text) {
        StringBuilder string = new StringBuilder();
            string.append(text);
            string.reverse();
        return string.toString().toCharArray();
    }

}
