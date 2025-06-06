package emp.emp.medical.controller;

import emp.emp.auth.custom.CustomUserDetails;
import emp.emp.medical.dto.request.MedicalResultRequest;
import emp.emp.medical.dto.response.FamilyMedicalResultResponse;
import emp.emp.medical.dto.response.MedicalResultResponse;
import emp.emp.medical.service.MedicalResultService;
import emp.emp.util.api_response.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auth/user/medical-results")
@RequiredArgsConstructor
public class MedicalResultController {

  private final MedicalResultService medicalResultService;

  /**
   * 진료 결과 등록
   * @param userDetails 인증된 사용자 정보
   * @param eventId 캘린더 이벤트 ID
   * @param request 진료 결과 등록 요청 데이터
   * @return 등록된 진료 결과 정보
   */
  @PostMapping("/{eventId}")
  public ResponseEntity<Response<MedicalResultResponse>> createMedicalResult(
          @AuthenticationPrincipal CustomUserDetails userDetails,
          @PathVariable Long eventId,
          @RequestBody MedicalResultRequest request
  ) {
    MedicalResultResponse response = medicalResultService.createMedicalResult(userDetails, eventId, request);
    return Response.ok(response).toResponseEntity();
  }

  /**
   * 진료결과 조회
   * @param userDetails 인증된 사용자 정보
   * @param eventId 캘린더 이벤트 신퀀스 ID
   * @return 진료 결과 정보
   */
  @GetMapping("/{eventId}")
  public ResponseEntity<Response<MedicalResultResponse>> getMedicalResult(
          @AuthenticationPrincipal CustomUserDetails userDetails,
          @PathVariable Long eventId
  ) {
    MedicalResultResponse response = medicalResultService.getMedicalResult(userDetails, eventId);
    return Response.ok(response).toResponseEntity();
  }

  /**
   * 진료 결과 수정
   * @param userDetails 인증된 사용자의 정보
   * @param eventId 캘린더 이벤트 시퀀스 ID
   * @param request 진료 결과 수정 요청 데이터
   * @return 수정된 진료 결과 정보
   */
  @PutMapping("/{eventId}")
  public ResponseEntity<Response<MedicalResultResponse>> updateMedicalResult(
          @AuthenticationPrincipal CustomUserDetails userDetails,
          @PathVariable Long eventId,
          @RequestBody MedicalResultRequest request
  ) {
    MedicalResultResponse response = medicalResultService.updateMedicalResult(userDetails, eventId, request);
    return Response.ok(response).toResponseEntity();
  }

  /**
   * 진료 결과 삭제
   * @param userDetails
   * @param eventId
   * @return
   */
  @DeleteMapping("/{eventId}")
  public ResponseEntity<Response<Void>> deleteMedicalResult(
          @AuthenticationPrincipal CustomUserDetails userDetails,
          @PathVariable Long eventId
  ) {
    medicalResultService.deleteMedicalResult(userDetails, eventId);
    return Response.ok().toResponseEntity();
  }

  /**
   * 가족 구성원의 공개된 진료결과 조회
   * @param userDetails
   * @return
   */
  @GetMapping("/family")
  public ResponseEntity<Response<List<FamilyMedicalResultResponse>>> getFamilyMedicalResults(
          @AuthenticationPrincipal CustomUserDetails userDetails
  ) {
    List<FamilyMedicalResultResponse> response = medicalResultService.getFamilyMedicalResult(userDetails);
    return Response.ok(response).toResponseEntity();
  }

}
