# Vouch

## Project Summary
Vouch is a Java application designed to manage internal communications through a moderated workflow. It uses the Model-View-Controller (MVC) pattern to separate content creation from publication. Regular Users can create and submit posts, while Admins have the exclusive authority to review, edit, and publish them to the official feed.

---

## Project Scope
Vouch focuses on two main user journeys:

- **User Journey:** Draft → Edit → Submit for Review  
- **Admin Journey:** Review Queue → Approve/Reject → Global Feed Management

---

## Technical Architecture (MVC)

### Model
- **User:** Stores credentials and role (`ADMIN` or `USER`)  
- **Post:** Stores title, body, author, and status (`PENDING`, `PUBLISHED`)

### View (User Interface)
- **Login Screen:** Role-based redirection after authentication  
- **User Dashboard:** "Create Post" form and personal submissions list  
- **Admin Dashboard:** Moderation Center showing all pending posts with "Approve" and "Reject" buttons  
- **Public Feed:** Read-only stream of all published posts

### Controller
- **PostController:** Handles logic for status transitions  
  - `submitPost()`: Changes status from `DRAFT` to `PENDING`  
  - `publishPost()`: Checks if logged-in user is an `ADMIN` before changing status to `PUBLISHED`

---

## Key Features

### For Users
- **Rich Text Drafting:** Simple interface to write announcements  
- **Personal Archive:** View history of own posts and their current status  

### For Admins
- **Moderation Queue:** View all posts awaiting approval  
- **One-Click Publishing:** Instantly push verified posts to the company feed

---

## Technology Stack
- **Language:** Java (JDK 17+)  
- **Architecture:** MVC (Model-View-Controller)  
- **Database:** MySQL  
- **UI Framework:** JSP

---

## Project Milestones
1. **Phase 1:** Database schema design and role-based user authentication  
2. **Phase 2:** User functionality — creating and submitting drafts  
3. **Phase 3:** Admin functionality — Moderation Queue and approval logic  
4. **Phase 4:** Final integration — testing the full workflow from draft to published

---

## Expected Outcome
A secure and reliable communication tool that eliminates unauthorized postings and ensures all company announcements meet professional standards before being visible organization-wide.

---

