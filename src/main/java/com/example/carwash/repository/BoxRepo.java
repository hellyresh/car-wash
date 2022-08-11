package com.example.carwash.repository;

import com.example.carwash.model.Box;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;

public interface BoxRepo extends JpaRepository<Box, Long> {
    @Query(value = """
            --New order
            --  start time:
            --      cast(:dateTime as time)
            --  end time:
            --      cast(cast(:dateTime as time) + interval '1 minute' * cast(ceil(b.time_coefficient *:duration) as int) as time)
            --  date:
            --      cast(:dateTime as date)
            --Existent order
            --  start time:
            --      cast(ord.date_time as time)
            --  end time:
            --      cast(ord.date_time as time) + interval '1 minute' * ord.duration
            --  date:
            --      cast(ord.date_time as date)
            select distinct b.* from boxes b
            join operators op on op.box_id=b.id
            where
                open_time <= cast(:dateTime as time)
            and
                close_time >= cast(cast(:dateTime as time) + interval '1 minute'
                * cast(ceil(b.time_coefficient *:duration) as int) as time)

            except

            select distinct b.* from boxes b
            join orders ord  on b.id =ord.box_id
            where
                cast(ord.date_time as date) = cast(:dateTime as date)
            and
                ord.status not in ('CANCELLED', 'FINISHED')
            and(
                (cast(ord.date_time as time) <= cast(:dateTime as time)
                and
                cast(:dateTime as time) < (cast(ord.date_time as time) + interval '1 minute' * ord.duration))
                or(
                    cast(ord.date_time as time) < cast(cast(:dateTime as time) +
                    interval '1 minute' * cast(ceil(b.time_coefficient *:duration) as int) as time)
                    and
                        cast(cast(:dateTime as time) +
                        interval '1 minute' * cast(ceil(b.time_coefficient *:duration) as int) as time) <=
                        (cast(ord.date_time as time) + interval '1 minute' * ord.duration)
                    )
                or (
                    cast(ord.date_time as time)
                        between
                            cast(:dateTime as time)
                        and
                            cast(cast(:dateTime as time) +
                            interval '1 minute' * cast(ceil(b.time_coefficient *:duration) as int) as time)
                    and
                    (cast(ord.date_time as time) + interval '1 minute' * ord.duration)
                        between
                            cast(:dateTime as time)
                        and
                            cast(cast(:dateTime as time) +
                            interval '1 minute' * cast(ceil(b.time_coefficient *:duration) as int) as time)
                    )
                )
                
            order by time_coefficient desc
            limit 1
            """, nativeQuery = true)
    Box findBestBox(@Param("dateTime") LocalDateTime dateTime, @Param("duration") Integer duration);


}

