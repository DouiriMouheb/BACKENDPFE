package Intranet.backend.repositories;

import Intranet.backend.dto.CountType;
import Intranet.backend.entites.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task,Long> {
    @Query(value="Select * from task order by due_date desc",nativeQuery = true)
    public List<Task> getAllTaskDueDateDesc();


    @Query(value="Select new Intranet.backend.dto.CountType(COUNT(*)/(Select COUNT(*) from Task) *100,type) from Task GROUP BY type")
    public List<CountType> getPercentageGroupByType();
}
