package CoursesEE.ModuleNumberThree;

/**
 * Created by Dazzling on 21.03.16.
 */

public interface Semaphore {

// ����������� ����������. ���� ���� ��������� ����������� ���. ���� ��� - ���������������� ����� �� ��� ��� ���� �� �������� ��������� ����������.

    public void acquire() throws InterruptedException;

    // ����������� ��������� ���������� ����������. ���� ���� ��������� ���������� ��������� ���������� ����������� ��.
    // ���� ��� - ���������������� ����� �� ��� ��� ���� �� �������� ��������� ���������� ��������� ����������.

    public void acquire(int permits) throws InterruptedException;

    // ��������� ���������� ��������� ��� ��������.

    public void release();

    // ��������� ��������� ���������� ���������� ��������� �� ��������.

    public void release(int permits);

    // ���������� ���������� ��������� ���������� �� ������ ������.

    public int getAvailablePermits();

}
