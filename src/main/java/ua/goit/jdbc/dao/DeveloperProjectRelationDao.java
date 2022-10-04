package ua.goit.jdbc.dao;

public class DeveloperProjectRelationDao {
    Integer developerId;
    String projectId;

    public DeveloperProjectRelationDao() {
    }

    public DeveloperProjectRelationDao(Integer developerId, String projectId) {
        this.developerId = developerId;
        this.projectId = projectId;
    }

    public Integer getDeveloperId() {
        return developerId;
    }

    public void setDeveloperId(Integer developerId) {
        this.developerId = developerId;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("DeveloperProjectRelationDao{");
        sb.append("developerId=").append(developerId);
        sb.append(", projectId='").append(projectId).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
