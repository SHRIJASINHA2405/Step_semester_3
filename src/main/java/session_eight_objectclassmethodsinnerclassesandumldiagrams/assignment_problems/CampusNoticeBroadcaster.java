package session_eight_objectclassmethodsinnerclassesandumldiagrams.assignment_problems;

import java.util.ArrayList;
import java.util.List;

// ================= STUDENT =================

class NoticeStudent {
    private final String studentId;
    private final String name;
    private final String department;
    private final List<NotificationChannel> preferredChannels;

    public NoticeStudent(
            String studentId,
            String name,
            String department) {

        this.studentId = studentId;
        this.name = name;
        this.department = department;
        this.preferredChannels = new ArrayList<>();
    }

    public String getStudentId() {
        return studentId;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    public void addPreferredChannel(NotificationChannel channel) {
        preferredChannels.add(channel);
    }

    public List<NotificationChannel> getPreferredChannels() {
        return preferredChannels;
    }
}

// ================= NOTIFICATION CHANNEL =================

interface NotificationChannel {
    void send(Notice notice, NoticeStudent student);
}

// ================= EMAIL CHANNEL =================

class EmailChannel implements NotificationChannel {

    @Override
    public void send(Notice notice, NoticeStudent student) {
        System.out.println(
                "[Email → "
                        + student.getName()
                        + "] "
                        + notice.getTitle()
        );
    }
}

// ================= SMS CHANNEL =================

class SmsChannel implements NotificationChannel {

    @Override
    public void send(Notice notice, NoticeStudent student) {
        System.out.println(
                "[SMS → "
                        + student.getName()
                        + "] "
                        + notice.getTitle()
        );
    }
}

// ================= APP CHANNEL =================

class AppChannel implements NotificationChannel {

    @Override
    public void send(Notice notice, NoticeStudent student) {
        System.out.println(
                "[App → "
                        + student.getName()
                        + "] "
                        + notice.getTitle()
        );
    }
}

// ================= NOTICE =================

class Notice {
    private final String title;
    private final List<String> targetDepartments;

    public Notice(
            String title,
            List<String> targetDepartments) {

        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Notice title is required."
            );
        }

        if (targetDepartments == null
                || targetDepartments.isEmpty()) {

            throw new IllegalArgumentException(
                    "At least one target department is required."
            );
        }

        this.title = title;
        this.targetDepartments =
                new ArrayList<>(targetDepartments);
    }

    public String getTitle() {
        return title;
    }

    public List<String> getTargetDepartments() {
        return targetDepartments;
    }

    public boolean targetsDepartment(String department) {
        return targetDepartments.contains(department);
    }
}

// ================= NOTICE BOARD =================

class NoticeBoard {

    private final List<NoticeStudent> students;

    public NoticeBoard() {
        students = new ArrayList<>();
    }

    public void addStudent(NoticeStudent student) {
        students.add(student);
    }

    public void postNotice(
            String title,
            List<String> targetDepartments) {

        Notice notice;

        try {
            notice = new Notice(
                    title,
                    targetDepartments
            );
        } catch (IllegalArgumentException e) {
            System.out.println(
                    "Cannot post notice: "
                            + e.getMessage()
            );
            return;
        }

        System.out.println(
                "Notice '"
                        + notice.getTitle()
                        + "' posted to "
                        + String.join(
                        ", ",
                        notice.getTargetDepartments()
                )
                        + "."
        );

        deliverNotice(notice);
    }

    private void deliverNotice(Notice notice) {

        for (NoticeStudent student : students) {

            if (notice.targetsDepartment(
                    student.getDepartment())) {

                for (NotificationChannel channel
                        : student.getPreferredChannels()) {

                    channel.send(notice, student);
                }
            }
        }
    }
}

// ================= MAIN CLASS =================

public class CampusNoticeBroadcaster {

    public static void main(String[] args) {

        NoticeBoard noticeBoard = new NoticeBoard();

        // Students
        NoticeStudent asha =
                new NoticeStudent(
                        "S001",
                        "Asha",
                        "CSE"
                );

        NoticeStudent ravi =
                new NoticeStudent(
                        "S002",
                        "Ravi",
                        "ECE"
                );

        // Preferred channels
        asha.addPreferredChannel(
                new EmailChannel()
        );

        asha.addPreferredChannel(
                new AppChannel()
        );

        ravi.addPreferredChannel(
                new SmsChannel()
        );

        // Add students to notice board
        noticeBoard.addStudent(asha);
        noticeBoard.addStudent(ravi);

        // Notice 1: CSE only
        List<String> cse =
                new ArrayList<>();

        cse.add("CSE");

        noticeBoard.postNotice(
                "Lab Closed Tomorrow",
                cse
        );

        System.out.println();

        // Notice 2: CSE and ECE
        List<String> cseAndEce =
                new ArrayList<>();

        cseAndEce.add("CSE");
        cseAndEce.add("ECE");

        noticeBoard.postNotice(
                "Fee Deadline Extended",
                cseAndEce
        );

        System.out.println();

        // Notice 3: No department
        List<String> noDepartment =
                new ArrayList<>();

        noticeBoard.postNotice(
                "Sports Day",
                noDepartment
        );
    }
}