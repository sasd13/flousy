package flousy.data.dao.accessor;

import proadmin.content.ListReports;
import proadmin.content.Report;
import proadmin.content.id.ReportId;
import proadmin.content.id.SquadId;

/**
 * Created by Samir on 11/06/2015.
 */
public interface ReportAccessor {

    void insertReport(Report report);

    void updateReport(Report report);

    void deleteReport(ReportId reportId);

    Report selectReport(ReportId reportId);

    ListReports selectReportsOfSquad(SquadId squadId);
}
