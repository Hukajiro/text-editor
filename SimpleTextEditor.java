import java.util.Scanner;
import java.util.Stack;

public class SimpleTextEditor {
    private StringBuilder currentText;  // Menyimpan teks yang ada
    private final Stack<String> historyStack;  // Stack untuk menyimpan versi sebelumnya
    public Stack<String> futureStack;   // Stack untuk menyimpan versi yang dibatalkan

    public SimpleTextEditor() {
        currentText = new StringBuilder();
        historyStack = new Stack<>();
        futureStack = new Stack<>();
    }

    // Menampilkan teks saat ini di editor
    public void displayText() {
        System.out.println("Teks saat ini: " + currentText.toString());
    }

    // Menambahkan teks ke editor
    public void appendText(String newText) {
        // Simpan keadaan teks sebelum perubahan ke historyStack
        historyStack.push(currentText.toString());
        // Kosongkan futureStack karena teks baru ditambahkan
        futureStack.clear();
        // Tambahkan teks baru ke currentText
        currentText.append(newText);
        System.out.println("Teks ditambahkan: " + newText);
    }

    // Mengembalikan teks ke keadaan sebelumnya
    public void undoAction() {
        if (!historyStack.isEmpty()) {
            futureStack.push(currentText.toString()); // Simpan teks saat ini ke futureStack
            currentText = new StringBuilder(historyStack.pop()); // Kembalikan keadaan sebelumnya
            System.out.println("Operasi undo berhasil.");
        } else {
            System.out.println("Tidak ada yang dapat di-undo.");
        }
    }

    // Mengulangi aksi yang telah dibatalkan
    public void redoAction() {
        if (!futureStack.isEmpty()) {
            historyStack.push(currentText.toString()); // Simpan keadaan saat ini ke historyStack
            currentText = new StringBuilder(futureStack.pop()); // Kembalikan keadaan yang dibatalkan
            System.out.println("Operasi redo berhasil.");
        } else {
            System.out.println("Tidak ada yang dapat di-redo.");
        }
    }

    public static void main(String[] args) {
        SimpleTextEditor textEditor = new SimpleTextEditor();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== Menu Text Editor ===");
            System.out.println("1. Tampilkan Teks");
            System.out.println("2. Tambah Teks");
            System.out.println("3. Undo");
            System.out.println("4. Redo");
            System.out.println("5. Keluar");
            System.out.print("Pilih opsi: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Membersihkan newline

            switch (choice) {
                case 1:
                    textEditor.displayText();
                    break;

                case 2:
                    System.out.print("Masukkan teks yang ingin ditambahkan: ");
                    String newText = scanner.nextLine();
                    textEditor.appendText(newText);
                    break;

                case 3:
                    textEditor.undoAction();
                    break;

                case 4:
                    textEditor.redoAction();
                    break;

                case 5:
                    System.out.println("Menutup aplikasi.");
                    scanner.close();
                    System.exit(0);

                default:
                    System.out.println("Pilihan tidak valid. Silakan coba lagi.");
            }
        }
    }
}
