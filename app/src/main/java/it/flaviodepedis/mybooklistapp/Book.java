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
    private String mAmount;

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
     * Link where reader the book
     */
    private String mWebReaderLink;

    /**
     * Constructs a new {@link Book} object.
     */
    public Book(String mTitle, String mAuthor, String mPublisher, String mPublishedDate,
                String mDescription, int mPageCount, String mPrintType, String mCategory,
                double mAverageRating, String mThumbnail, String mAmount, String mCurrencyCode,
                boolean isEbook, boolean isEpub, boolean isPdf, String mBuyLink, String mWebReaderLink) {

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
        this.mWebReaderLink = mWebReaderLink;
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
    public String getmAmount() {
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
    public boolean getIsEbook() {
        return isEbook;
    }

    /**
     * Returns the ePub format type of the book.
     */
    public boolean getIsEpub() {
        return isEpub;
    }

    /**
     * Returns the PDF format type of the book.
     */
    public boolean getIsPdf() {
        return isPdf;
    }

    /**
     * Returns the link to buy the book.
     */
    public String getmBuyLink() {
        return mBuyLink;
    }

    /**
     * Returns the link to reader the book.
     */
    public String getmWebReaderLink() {
        return mWebReaderLink;
    }


}
