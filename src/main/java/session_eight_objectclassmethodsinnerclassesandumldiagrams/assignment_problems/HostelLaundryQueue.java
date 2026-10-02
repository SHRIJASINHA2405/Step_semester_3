package session_eight_objectclassmethodsinnerclassesandumldiagrams.assignment_problems;

// ================= STUDENT =================

class LaundryStudent {

    private final String studentId;
    private final String name;

    public LaundryStudent(String studentId, String name) {
        this.studentId = studentId;
        this.name = name;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getName() {
        return name;
    }
}

// ================= WASH TYPE =================

abstract class WashType {

    public abstract String getName();

    public abstract int getDuration();

    public abstract double getCharge();
}

// ================= QUICK WASH =================

class QuickWash extends WashType {

    @Override
    public String getName() {
        return "Quick";
    }

    @Override
    public int getDuration() {
        return 30;
    }

    @Override
    public double getCharge() {
        return 20.00;
    }
}

// ================= NORMAL WASH =================

class NormalWash extends WashType {

    @Override
    public String getName() {
        return "Normal";
    }

    @Override
    public int getDuration() {
        return 45;
    }

    @Override
    public double getCharge() {
        return 30.00;
    }
}

// ================= HEAVY WASH =================

class HeavyWash extends WashType {

    @Override
    public String getName() {
        return "Heavy";
    }

    @Override
    public int getDuration() {
        return 60;
    }

    @Override
    public double getCharge() {
        return 45.00;
    }
}

// ================= WASHING MACHINE =================

class WashingMachine {

    private final String machineId;
    private boolean busy;

    public WashingMachine(String machineId) {
        this.machineId = machineId;
        this.busy = false;
    }

    public String getMachineId() {
        return machineId;
    }

    public boolean isBusy() {
        return busy;
    }

    // Only the machine itself changes its state
    private void startMachine() {
        busy = true;
    }

    private void freeMachine() {
        busy = false;
    }

    public WashCycle startWash(
            LaundryStudent student,
            WashType washType) {

        if (busy) {
            System.out.println(
                    "Machine " + machineId + " is currently busy."
            );
            return null;
        }

        startMachine();

        WashCycle cycle =
                new WashCycle(
                        student,
                        this,
                        washType
                );

        System.out.printf(
                "%s wash started on %s for %s (%d min). Charge: ₹%.2f.%n",
                washType.getName(),
                machineId,
                student.getName(),
                washType.getDuration(),
                washType.getCharge()
        );

        return cycle;
    }

    public void completeWash(WashCycle cycle) {

        if (!busy) {
            System.out.println(
                    "Machine " + machineId + " is already free."
            );
            return;
        }

        freeMachine();

        System.out.println(
                machineId + " cycle completed. "
                        + machineId + " is now free."
        );
    }
}

// ================= WASH CYCLE =================

class WashCycle {

    private final LaundryStudent student;
    private final WashingMachine machine;
    private final WashType washType;

    public WashCycle(
            LaundryStudent student,
            WashingMachine machine,
            WashType washType) {

        this.student = student;
        this.machine = machine;
        this.washType = washType;
    }

    public LaundryStudent getStudent() {
        return student;
    }

    public WashingMachine getMachine() {
        return machine;
    }

    public WashType getWashType() {
        return washType;
    }

    public double calculateCharge() {
        return washType.getCharge();
    }

    public int getDuration() {
        return washType.getDuration();
    }
}

// ================= MAIN CLASS =================

public class HostelLaundryQueue {

    public static void main(String[] args) {

        // Create students
        LaundryStudent asha =
                new LaundryStudent("S001", "Asha");

        LaundryStudent ravi =
                new LaundryStudent("S002", "Ravi");

        LaundryStudent neha =
                new LaundryStudent("S003", "Neha");

        // Create machines
        WashingMachine machineM1 =
                new WashingMachine("M1");

        WashingMachine machineM2 =
                new WashingMachine("M2");

        // Create wash types
        WashType quickWash =
                new QuickWash();

        WashType normalWash =
                new NormalWash();

        WashType heavyWash =
                new HeavyWash();

        // Asha starts Quick wash on M1
        WashCycle ashaCycle =
                machineM1.startWash(
                        asha,
                        quickWash
                );

        System.out.println();

        // Ravi attempts Heavy wash on busy M1
        machineM1.startWash(
                ravi,
                heavyWash
        );

        System.out.println();

        // Ravi starts Heavy wash on M2
        WashCycle raviCycle =
                machineM2.startWash(
                        ravi,
                        heavyWash
                );

        System.out.println();

        // M1 completes its cycle
        machineM1.completeWash(ashaCycle);

        System.out.println();

        // Neha starts Normal wash on M1
        machineM1.startWash(
                neha,
                normalWash
        );
    }
}