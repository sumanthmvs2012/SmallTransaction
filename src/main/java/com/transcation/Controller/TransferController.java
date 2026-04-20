package com.transcation.Controller;

import com.transcation.DTO.TransferRequestDto;
import com.transcation.Service.TransferService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transfer")
public class TransferController {

    private final TransferService transferService;

    public TransferController(TransferService transferService) {
        this.transferService = transferService;
    }

    @PostMapping
    public String transfer(@RequestBody TransferRequestDto dto) {
        return transferService.transferMoney(dto);
    }
}
