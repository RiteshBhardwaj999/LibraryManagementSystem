package com.library.model;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Represents a record of a borrowed book by a patron.
 * This class is mostly immutable except for returnDate,
 * which is updated when the book is returned.
 */
public class BorrowingRecord {

    private final String bookId;
    private final String bookTitle;
    private final LocalDate borrowDate;
    private LocalDate returnDate;

    public BorrowingRecord(String bookId, String bookTitle, LocalDate borrowDate) {

        if (bookId == null || bookId.isBlank()) {
            throw new IllegalArgumentException("Book ID cannot be null or empty.");
        }

        if (bookTitle == null || bookTitle.isBlank()) {
            throw new IllegalArgumentException("Book title cannot be null or empty.");
        }

        if (borrowDate == null) {
            throw new IllegalArgumentException("Borrow date cannot be null.");
        }

        this.bookId = bookId;
        this.bookTitle = bookTitle;
        this.borrowDate = borrowDate;
        this.returnDate = null; // Initially not returned
    }

    public String getBookId() {
        return bookId;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    /**
     * Marks the book as returned by setting return date.
     */
    public void markAsReturned(LocalDate returnDate) {
        if (returnDate == null) {
            throw new IllegalArgumentException("Return date cannot be null.");
        }
        if (returnDate.isBefore(borrowDate)) {
            throw new IllegalArgumentException("Return date cannot be before borrow date.");
        }
        this.returnDate = returnDate;
    }

    public boolean isReturned() {
        return returnDate != null;
    }

    @Override
    public String toString() {
        return "BorrowingRecord{" +
                "bookId='" + bookId + '\'' +
                ", bookTitle='" + bookTitle + '\'' +
                ", borrowDate=" + borrowDate +
                ", returnDate=" + returnDate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BorrowingRecord)) return false;
        BorrowingRecord that = (BorrowingRecord) o;
        return Objects.equals(bookId, that.bookId)
                && Objects.equals(borrowDate, that.borrowDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookId, borrowDate);
    }
}