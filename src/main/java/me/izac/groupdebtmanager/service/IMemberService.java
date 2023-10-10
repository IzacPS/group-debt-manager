package me.izac.groupdebtmanager.service;

import me.izac.groupdebtmanager.dto.MemberDto;
import me.izac.groupdebtmanager.model.Member;

import java.util.List;

public interface IMemberService {

    MemberDto createMember(MemberDto memberDTO);
    List<MemberDto> listAllMembers();
    MemberDto findMemberByEmail(String email);
    MemberDto findMemberById(Long memberId);
    void deleteMemberByEmail(String email);
    void deleteMemberById(Long memberId);
}
