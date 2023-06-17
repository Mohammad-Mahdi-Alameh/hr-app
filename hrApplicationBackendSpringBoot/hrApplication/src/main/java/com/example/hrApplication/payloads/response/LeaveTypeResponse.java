package com.example.hrApplication.payloads.response;

import java.util.List;

public class LeaveTypeResponse {
    private Long id;

    private String name;

    private List<LeaveResponse> leaves;
    
    public LeaveTypeResponse(Long id, String name, List<LeaveResponse> leaves) {
        this.id = id;
        this.name = name;
        this.leaves = leaves;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<LeaveResponse> getLeaves() {
        return leaves;
    }

    public void setLeaves(List<LeaveResponse> leaves) {
        this.leaves = leaves;
    } 
}
