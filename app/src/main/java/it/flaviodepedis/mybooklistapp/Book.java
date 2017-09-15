package it.flaviodepedis.mybooklistapp;

import java.io.Serializable;

/**
 * Created by flavio.depedis on 15/09/2017.
 */

public class Book implements Serializable {

    /**
     * Title of the book
     */
    private String mTitle;

    /**
     * Author of the book
     */
    private String mAuthor;

    /**
     * Editor of the book
     */
    private String mPublisher;

    /**
     * Date of publish book
     */
    private String mPublishedDate;

    /**
     * Main description of the book
     */
    private String mDescription;

    /**
     * Count of the pages of the book
     */
    private int mPageCount;

    /**
     * Type of book
     */
    private String mPrintType;

    /**
     * Category of the book
     */
    private String mCategory;

    /**
     * Average Rating of the book
     */
    private double mAverageRating;

    /**
     * Image of the book
     */
    private String mThumbnail;

    /**
     * Price of the book
     */
    private double mAmount;

    /**
     * Currency of the book
     */
    private String mCurrencyCode;

    /**
     * eBook format of the book
     */
    private boolean isEbook;

    /**
     * ePub format of the book
     */
    private boolean isEpub;

    /**
     * PDF format of the book
     */
    private boolean isPdf;

    /**
     * Link where buying the book
     */
    private String mBuyLink;

    /**
     * Constructs a new {@link Book} object.
     */
    public Book(String mTitle, String mAuthor, String mPublisher, String mPublishedDate,
                String mDescription, int mPageCount, String mPrintType, String mCategory,
                double mAverageRating, String mThumbnail, double mAmount, String mCurrencyCode,
                boolean isEbook, boolean isEpub, boolean isPdf, String mBuyLink) {

        this.mTitle = mTitle;
        this.mAuthor = mAuthor;
        this.mPublisher = mPublisher;
        this.mPublishedDate = mPublishedDate;
        this.mDescription = mDescription;
        this.mPageCount = mPageCount;
        this.mPrintType = mPrintType;
        this.mCategory = mCategory;
        this.mAverageRating = mAverageRating;
        this.mThumbnail = mThumbnail;
        this.mAmount = mAmount;
        this.mCurrencyCode = mCurrencyCode;
        this.isEbook = isEbook;
        this.isEpub = isEpub;
        this.isPdf = isPdf;
        this.mBuyLink = mBuyLink;
    }

    /**
     * Returns the title of the book.
     */
    public String getmTitle() {
        return mTitle;
    }

    /**
     * Returns the author of the book.
     */
    public String getmAuthor() {
        return mAuthor;
    }

    /**
     * Returns the editor publisher of the book.
     */
    public String getmPublisher() {
        return mPublisher;
    }

    /**
     * Returns the published date of the book.
     */
    public String getmPublishedDate() {
        return mPublishedDate;
    }

    /**
     * Returns the description of the book.
     */
    public String getmDescription() {
        return mDescription;
    }

    /**
     * Returns the total pages of the book.
     */
    public int getmPageCount() {
        return mPageCount;
    }

    /**
     * Returns the print type of the book.
     */
    public String getmPrintType() {
        return mPrintType;
    }

    /**
     * Returns the category of the book.
     */
    public String getmCategory() {
        return mCategory;
    }

    /**
     * Returns the rating of the book.
     */
    public double getmAverageRating() {
        return mAverageRating;
    }

    /**
     * Returns the image of the book.
     */
    public String getmThumbnail() {
        return mThumbnail;
    }

    /**
     * Returns the price of the book.
     */
    public double getmAmount() {
        return mAmount;
    }

    /**
     * Returns the currency of the price of the book.
     */
    public String getmCurrencyCode() {
        return mCurrencyCode;
    }

    /**
     * Returns the eBook format type of the book.
     */
    public boolean isEbook() {
        return isEbook;
    }

    /**
     * Returns the ePub format type of the book.
     */
    public boolean isEpub() {
        return isEpub;
    }

    /**
     * Returns the PDF format type of the book.
     */
    public boolean isPdf() {
        return isPdf;
    }

    /**
     * Returns the link to buy the book.
     */
    public String getmBuyLink() {
        return mBuyLink;
    }

    /**
     * Set the title of the book.
     */
    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    /**
     * Set the author of the book.
     */
    public void setmAuthor(String mAuthor) {
        this.mAuthor = mAuthor;
    }

    /**
     * Set the editor publisher of the book.
     */
    public void setmPublisher(String mPublisher) {
        this.mPublisher = mPublisher;
    }

    /**
     * Set the published date of the book.
     */
    public void setmPublishedDate(String mPublishedDate) {
        this.mPublishedDate = mPublishedDate;
    }

    /**
     * Set the description of the book.
     */
    public void setmDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    /**
     * Set the total pages of the book.
     */
    public void setmPageCount(int mPageCount) {
        this.mPageCount = mPageCount;
    }

    /**
     * Set the print type of the book.
     */
    public void setmPrintType(String mPrintType) {
        this.mPrintType = mPrintType;
    }

    /**
     * Set the category of the book.
     */
    public void setmCategory(String mCategory) {
        this.mCategory = mCategory;
    }

    /**
     * Set the rating of the book.
     */
    public void setmRating(double mAverageRating) {
        this.mAverageRating = mAverageRating;
    }

    /**
     * Set the image of the book.
     */
    public void setmThumbnail(String mThumbnail) {
        this.mThumbnail = mThumbnail;
    }

    /**
     * Set the price of the book.
     */
    public void setmAmount(double mAmount) {
        this.mAmount = mAmount;
    }

    /**
     * Set the currency of the price of the book.
     */
    public void setmCurrencyCode(String mCurrencyCode) {
        this.mCurrencyCode = mCurrencyCode;
    }

    /**
     * Set the eBook format type of the book.
     */
    public void setEbook(boolean ebook) {
        isEbook = ebook;
    }

    /**
     * Set the ePub format type of the book.
     */
    public void setEpub(boolean epub) {
        isEpub = epub;
    }

    /**
     * Set the PDF format type of the book.
     */
    public void setPdf(boolean pdf) {
        isPdf = pdf;
    }

    /**
     * Set the link to buy the book.
     */
    public void setmBuyLink(String mBuyLink) {
        this.mBuyLink = mBuyLink;
    }
}
