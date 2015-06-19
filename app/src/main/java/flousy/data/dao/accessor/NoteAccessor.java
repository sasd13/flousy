package flousy.data.dao.accessor;

import proadmin.content.MapNotes;
import proadmin.content.id.ReportId;

/**
 * Created by Samir on 11/06/2015.
 */
public interface NoteAccessor {

    void insertNotes(MapNotes mapNotes, ReportId reportId);

    void updateNotes(MapNotes mapNotes, ReportId reportId);

    MapNotes selectNotesOfReport(ReportId reportId);
}
