package me.izac.groupdebtmanager.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import me.izac.groupdebtmanager.dto.MemberDto;
import me.izac.groupdebtmanager.model.Member;
import me.izac.groupdebtmanager.repository.MemberRepository;
import me.izac.groupdebtmanager.service.IMemberService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements IMemberService {
    private final MemberRepository memberRepository;

    @Override
    public MemberDto createMember(MemberDto memberDTO){
        Member aMember = Member.builder()
                .firstName(memberDTO.getFirstName())
                .lastName(memberDTO.getLastName())
                .email(memberDTO.getEmail())
                .password(memberDTO.getPassword())
                .build();

        return memberRepository.save(aMember).toMemberDto();
    }

    @Override
    public List<MemberDto> listAllMembers(){
        return memberRepository.findAll().stream().map(Member::toMemberDto).toList();
    }

    @Override
    public MemberDto findMemberByEmail(String email){
        return memberRepository.findMemberByEmail(email).orElseThrow(() -> new EntityNotFoundException("User not found")).toMemberDto();
    }

    @Override
    public MemberDto findMemberById(Long memberId) {
        return memberRepository.findById(memberId).orElseThrow(() -> new EntityNotFoundException("User not found")).toMemberDto();
    }

    @Override
    public void deleteMemberByEmail(String email){
        memberRepository.deleteMemberByEmail(email);
    }

    @Override
    public void deleteMemberById(Long memberId) {
        memberRepository.deleteById(memberId);
    }
}
