package me.izac.groupdebtmanager.controller;

import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import me.izac.groupdebtmanager.dto.MemberDto;
import me.izac.groupdebtmanager.model.Member;
import me.izac.groupdebtmanager.service.IMemberService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {
    private final IMemberService memberService;


    @GetMapping("/all")
    List<MemberDto> getMembers(){
        return memberService.listAllMembers();
    }

    @PostMapping
    MemberDto createMember(@RequestBody MemberDto memberDto){
        return memberService.createMember(memberDto);
    }

    @GetMapping("/{id}")
    MemberDto getMemberById(@PathVariable Long id){
        return memberService.findMemberById(id);
    }

    @GetMapping("/")
    MemberDto getMemberByEmail(@RequestParam String email){
        return memberService.findMemberByEmail(email);
    }
}
