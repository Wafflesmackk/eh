import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class PosChecker {

    public void checkPositionsInFile(String[] args){
        String fileName = args[0];

        try (FileChannel fileChannel = FileChannel.open(Path.of("src",fileName),StandardOpenOption.READ)) {
            for (int i = 1; i < args.length; i += 2) {
                long position = Long.parseLong(args[i]);
                byte valueToCheck = Byte.parseByte(args[i + 1]);
                ByteBuffer buffer = ByteBuffer.allocate(1);
                fileChannel.read(buffer, position);
                buffer.flip();
                byte fileValue = buffer.get();
                if (fileValue != valueToCheck) {
                    System.out.println("Error");
                } else {
                    System.out.println("Correct for:" + position);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
