package edu.uapa.web.app.gamify.domains.gamifies;

import edu.uapa.web.app.gamify.models.abstracts.Auditable;


import javax.persistence.*;

@Entity(name = "solutions")
public class Solution extends Auditable {
    @Column(nullable = false)
    private String solution;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "problem_id")
    private Problem problem;

    @Column(nullable = false)
    private boolean is_correct;

    public Solution() {
    }

    public Solution(String solution, Problem problem, boolean is_correct) {
        this.solution = solution;
        this.problem = problem;
        this.is_correct = is_correct;
    }

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }

    public Problem getProblem() {
        return problem;
    }

    public void setProblem(Problem problem) {
        this.problem = problem;
    }

    public boolean isIs_correct() {
        return is_correct;
    }

    public void setIs_correct(boolean is_correct) {
        this.is_correct = is_correct;
    }
}
