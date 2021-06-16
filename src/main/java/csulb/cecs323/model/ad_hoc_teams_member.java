package csulb.cecs323.model;

import javax.persistence.*;

@Entity
public class ad_hoc_teams_member {
    @Id
    @PrimaryKeyJoinColumn(foreignKey = @ForeignKey(name = "AD_HOC_TEAMS_EMAIL"))
    @PrimaryKeyJoinColumn(foreignKey = @ForeignKey(name = "INDIVIDUAL_AUTHOR_EMAIL"))

    @ManyToOne
    @JoinTable(name = "AD_HOC_TEAMS_MEMBER", joinColumns = @JoinColumn(name = "AD_HOC_TEAMS_EMAIL"),
            inverseJoinColumns = @JoinColumn(name = "INDIVIDUAL_AUTHORS_EMAIL"))
    private individual_author individual_authors_email;

    @ManyToOne
    @JoinTable(name = "INDIVIDUAL_AUTHOR_EMAIL", joinColumns = @JoinColumn(name = "INDIVIDUAL_AUTHOR_EMAIL"),
            inverseJoinColumns = @JoinColumn(name = "AD_HOC_TEAMS_MEMBER"))
    private ad_hoc_teams_member ad_hoc_teams_member;
}