import lombok.RequiredArgsConstructor;
import service.impl.IManagmentService;
@RequiredArgsConstructor
public class Main {
    public static void main(String[] args) {
        new IManagmentService().manage();
    }
}
