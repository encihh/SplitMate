# SplitMate

SplitMate is a Java console application for managing group expenses and tracking payments. It provides menu-driven options for creating groups, recording expenses, editing group details, tracking payments, and generating summaries.

## Features
- **Expense Management**
  - Create new groups by entering a group name, adding members, and recording expenses with descriptions and amounts.
  - Edit existing groups: rename, add members, remove members, or delete groups.
- **Payment Tracking**
  - Manage member shares by calculating fair contributions based on total expenses.
  - Record payments between members and update balances.
  - View outstanding balances and receivables.
- **Summary Generation**
  - Display detailed summaries of group expenses, contributions, and balances.
  - Save summaries to `output.txt` for record keeping.
- **Menu Navigation**
  - Console-based menus guide the user through expense management, group editing, payment tracking, and summary viewing.

## Project Structure
- `SplitMate.java` — main entry point, handles menus and navigation  
- `Group.java` — defines group structure, members, and expenses  
- `GroupManager.java` — manages groups (create, search, edit, delete)  
- `TrackPayment.java` — handles contributions, balances, and payment tracking  
- `Utilities.java` — helper methods (clear screen, menus, delays)  

## How to Run
1. Clone the repository:
   ```bash
   git clone https://github.com/encihh/SplitMate.git
   cd SplitMate/src
