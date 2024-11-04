import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @GetMapping("/api/v1/user")
    public String getEmployeeId(@RequestHeader("Host") String host) {
        // Host 헤더에서 사번 추출
        String empId = extractEmployeeIdFromHost(host);
        
        // 응답으로 사번 반환
        return "Employee ID: " + empId;
    }

    private String extractEmployeeIdFromHost(String host) {
        // 예시 호스트 형식: sampleapp1-12345.cepg-aa.kubepia.net
        if (host == null || !host.contains(".")) {
            return "Invalid Host";
        }
        
        // "-" 뒤와 "." 앞의 사번 추출
        String[] parts = host.split("\\.");
        if (parts.length > 0 && parts[0].contains("-")) {
            return parts[0].split("-")[1];
        } else {
            return "Invalid Host Format";
        }
    }
}
