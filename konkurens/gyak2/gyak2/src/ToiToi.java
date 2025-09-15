public class ToiToi {
    private String user = "";
    public synchronized void use(String user) {
        System.out.println(user + " enters");
        if (this.user != "") {
            System.out.println(this.user + " freaks out");
            System.out.println(user + " is disappointed");
            System.out.println(user + " leaves");
        }
        else {
            this.user = user;
            try {
                Thread.sleep(1000);
            } catch(InterruptedException ie) {ie.printStackTrace();}
            System.out.println(user + " leaves");
            this.user = "";
        }
    }
}
