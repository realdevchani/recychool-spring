package com.app.recychool.api.privateapi;

import com.app.recychool.domain.dto.reserve.ReserveCreateRequestDTO;
import com.app.recychool.domain.dto.reserve.ReserveCreateResponseDTO;
import com.app.recychool.domain.enums.ReserveType;
import com.app.recychool.service.AuthService;
import com.app.recychool.service.ReserveService;
import com.app.recychool.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController("privateReserveApi")
@RequestMapping("/private/schools")
@RequiredArgsConstructor
public class ReserveApi {

    private final ReserveService reserveService;
    private final AuthService authService;
    private final UserService userService;

    // 장소대여 예약
    @PostMapping("/{schoolId}/place/reserves")
    public ReserveCreateResponseDTO reservePlace(
            Authentication authentication,
            @PathVariable Long schoolId,
            @RequestBody ReserveCreateRequestDTO requestDTO
    ) {
        String userEmail =
                authService.getUserEmailFromAuthentication(authentication);

        Long userId =
                userService.getUserIdByUserEmail(userEmail);

        return reserveService.createReserve(
                userId,
                schoolId,
                ReserveType.PLACE,
                requestDTO
        );
    }

    // 주차 예약
    @PostMapping("/{schoolId}/parking/reserves")
    public ReserveCreateResponseDTO reserveParking(
            Authentication authentication,
            @PathVariable Long schoolId,
            @RequestBody ReserveCreateRequestDTO requestDTO
    ) {
        String userEmail =
                authService.getUserEmailFromAuthentication(authentication);

        Long userId =
                userService.getUserIdByUserEmail(userEmail);

        return reserveService.createReserve(
                userId,
                schoolId,
                ReserveType.PARKING,
                requestDTO
        );
    }

}
