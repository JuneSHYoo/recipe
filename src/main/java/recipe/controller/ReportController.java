package recipe.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import recipe.entity.Report;
import recipe.entity.Report.ReportStatus;
import recipe.entity.User;
import recipe.service.AdminService;
import recipe.service.ReportService;

@RestController
@RequestMapping("/reports")
public class ReportController {

	@Autowired
    private ReportService reportService;
	
	@Autowired
	private AdminService adminService;

	//레시피 신고
    @PostMapping("/{recipeId}")
    @Operation(summary = "레시피 신고", description = "일반 사용자의 레시피 신고 기능")
    public ResponseEntity<String> reportPost(@PathVariable Long recipeId, @RequestParam String reason) {
        reportService.reportRecipe(recipeId, reason);
        return ResponseEntity.ok("신고가 접수되었습니다.");
    }
    
    // 관리자의 신고 목록 조회
    @GetMapping
    @Operation(summary = "신고목록 조회", description = "관리자의 신고 목록 조회 기능")
    public ResponseEntity<List<Report>> getReportsForAdmin(@RequestBody User admin) {
        List<Report> reports = adminService.getAllPendingReports(admin);
        return ResponseEntity.ok(reports);
    }

    // 관리자의 신고 처리
    @PostMapping("/{reportId}")
    @Operation(summary = "신고 처리 기능", description = "관리자의 신고 처리 기능")
    public ResponseEntity<String> resolveReport(@RequestBody User admin, @PathVariable Long reportId, @RequestParam ReportStatus status) {
        adminService.updateReportStatus(admin, reportId, status);
        return ResponseEntity.ok("신고가 처리되었습니다.");
    }
}
