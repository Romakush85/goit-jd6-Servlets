package ua.goit.jdbc.dao;

public class DeveloperSkillRelationDao {
    Integer developerId;
    Integer skillId;

    public DeveloperSkillRelationDao() {
    }

    public DeveloperSkillRelationDao(Integer developerId, Integer skillId) {
        this.developerId = developerId;
        this.skillId = skillId;
    }

    public Integer getDeveloperId() {
        return developerId;
    }

    public void setDeveloperId(Integer developerId) {
        this.developerId = developerId;
    }

    public Integer getSkillId() {
        return skillId;
    }

    public void setSkillId(Integer skillId) {
        this.skillId = skillId;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("DeveloperSkillRelationDao{");
        sb.append("developerId=").append(developerId);
        sb.append(", skillId=").append(skillId);
        sb.append('}');
        return sb.toString();
    }
}
