# Project Structure & Coding Standards

> Put this file at the root of your repo as `PROJECT_STRUCTURE_GUIDE.md` (or `docs/PROJECT_STRUCTURE_GUIDE.md`).
> Every time you ask for a new feature/module, refer back to this file so structure stays consistent.

---

## 1. High-Level Idea

One platform, multiple "activity modules" that all behave the same way (list, detail, book/join, admin-manage):

- Travel
- Fitness
- Calisthenics
- Party Place
- Meet New Friends Nearby
- Sports Activity
- Event Booking
- (new modules will follow the exact same pattern — see Section 9)

Two apps:
- **Backend**: Spring Boot, layered MVC, package-by-module
- **Frontend**: React, folder-per-module
- **Admin Panel**: a module inside the frontend that manages data for every other module

---

## 2. Repo / Root Layout

```
project-root/
├── backend/                # Spring Boot app
├── frontend/                # React app (public/user side)
├── admin-frontend/          # (Option A) separate React app for admin
│                              OR keep admin inside frontend/src/modules/admin (Option B - recommended to start)
├── docs/
│   └── PROJECT_STRUCTURE_GUIDE.md   <-- this file
└── docker-compose.yml       # optional, for local db + backend + frontend
```

**Recommendation:** Start with Option B (admin inside the same React app, behind `/admin` routes + role check). Split into a separate app later only if it grows too large.

---

## 3. Backend Structure (Spring Boot, MVC, package-by-module)

```
backend/
└── src/main/java/com/yourcompany/appname/
    ├── AppnameApplication.java
    │
    ├── config/                     # cross-cutting config
    │   ├── SecurityConfig.java
    │   ├── CorsConfig.java
    │   ├── SwaggerConfig.java
    │   └── JacksonConfig.java
    │
    ├── security/
    │   ├── jwt/
    │   ├── filter/
    │   └── CustomUserDetailsService.java
    │
    ├── common/                     # shared across all modules
    │   ├── exception/
    │   │   ├── GlobalExceptionHandler.java
    │   │   ├── ResourceNotFoundException.java
    │   │   └── BadRequestException.java
    │   ├── response/
    │   │   ├── ApiResponse.java     # standard {success, message, data}
    │   │   └── PageResponse.java
    │   ├── util/
    │   └── constant/
    │
    ├── user/                       # auth + profile (not a "content" module)
    │   ├── controller/
    │   ├── service/
    │   │   └── impl/
    │   ├── repository/
    │   ├── entity/
    │   └── dto/
    │       ├── request/
    │       └── response/
    │
    ├── modules/
    │   ├── travel/
    │   │   ├── controller/         # TravelController.java
    │   │   ├── service/
    │   │   │   ├── TravelService.java
    │   │   │   └── impl/TravelServiceImpl.java
    │   │   ├── repository/         # TravelRepository.java (extends JpaRepository)
    │   │   ├── entity/              # Travel.java
    │   │   ├── dto/
    │   │   │   ├── request/         # TravelCreateRequest.java
    │   │   │   └── response/        # TravelResponse.java
    │   │   └── mapper/              # TravelMapper.java (entity <-> dto)
    │   │
    │   ├── fitness/          (same sub-structure)
    │   ├── calisthenics/     (same sub-structure)
    │   ├── partyplace/       (same sub-structure)
    │   ├── friendsnearby/    (same sub-structure)
    │   ├── sportsactivity/   (same sub-structure)
    │   └── eventbooking/     (same sub-structure)
    │
    └── admin/
        ├── controller/               # AdminDashboardController, AdminUserController...
        ├── service/
        └── dto/
```

**Layer rule (strict MVC flow):**
`Controller → Service (interface) → ServiceImpl → Repository → Entity/DB`
Controllers never touch repositories directly. DTOs go in/out of controllers; entities never leave the service layer.

### `resources/`
```
resources/
├── application.yml
├── application-dev.yml
├── application-prod.yml
└── db/migration/            # Flyway scripts: V1__init.sql, V2__add_travel.sql ...
```
Use **Flyway** (or Liquibase) from day one — every schema change is a numbered script, never manual DB edits.

---

## 4. Frontend Structure (React, module-wise)

```
frontend/
└── src/
    ├── api/
    │   ├── axiosClient.js        # base instance, interceptors, auth token
    │   ├── travel.api.js
    │   ├── fitness.api.js
    │   ├── calisthenics.api.js
    │   ├── partyPlace.api.js
    │   ├── friendsNearby.api.js
    │   ├── sportsActivity.api.js
    │   ├── eventBooking.api.js
    │   └── admin.api.js
    │
    ├── components/                # shared/dumb components only
    │   ├── ui/                    # Button, Card, Modal, Input...
    │   └── layout/                # Navbar, Footer, Sidebar
    │
    ├── layouts/
    │   ├── MainLayout.jsx
    │   ├── AuthLayout.jsx
    │   └── AdminLayout.jsx
    │
    ├── modules/
    │   ├── travel/
    │   │   ├── components/        # TravelCard, TravelFilter...
    │   │   ├── pages/              # TravelListPage, TravelDetailPage
    │   │   ├── hooks/              # useTravelList()
    │   │   └── store/              # travelSlice.js (if Redux) or context
    │   ├── fitness/          (same sub-structure)
    │   ├── calisthenics/     (same sub-structure)
    │   ├── partyPlace/       (same sub-structure)
    │   ├── friendsNearby/    (same sub-structure)
    │   ├── sportsActivity/   (same sub-structure)
    │   ├── eventBooking/     (same sub-structure)
    │   ├── auth/               # login/register/profile
    │   └── admin/
    │       ├── dashboard/        # charts/stats
    │       ├── userManagement/
    │       ├── travelManagement/     # CRUD screens per module
    │       ├── fitnessManagement/
    │       ├── ...ManagementPerModule
    │       └── settings/
    │
    ├── routes/
    │   ├── AppRoutes.jsx
    │   ├── PrivateRoute.jsx        # logged-in only
    │   └── AdminRoute.jsx          # role=ADMIN only
    │
    ├── store/                     # redux store config (root)
    ├── hooks/                     # global hooks (useAuth, useDebounce)
    ├── utils/
    ├── constants/
    └── App.jsx
```

**Rule:** a component only goes in `components/` (shared) if 2+ modules use it. Otherwise it lives inside that module's own `components/` folder.

---

## 5. Admin Panel — what it should cover

- **Dashboard**: counts/charts per module (bookings today, active users, revenue if applicable)
- **User management**: list/block/roles
- **Per-module CRUD**: create/edit/delete/publish items for Travel, Fitness, Calisthenics, Party Place, Sports Activity, Event Booking listings
- **Bookings/Requests**: approve/reject bookings or friend-nearby requests
- **Media**: image upload management for listings
- **Settings**: categories, tags, locations master data

Every module's admin screens should reuse the same table/form pattern (generic `<DataTable>` + `<EntityForm>` components) so adding a new module's admin UI is fast.

---

## 6. API Conventions

- Base path: `/api/v1/{module}` e.g. `/api/v1/travel`, `/api/v1/event-booking`
- Admin path: `/api/v1/admin/{module}`
- Standard response wrapper:
```json
{
  "success": true,
  "message": "Fetched successfully",
  "data": { }
}
```
- Pagination: `?page=0&size=10&sort=createdAt,desc`
- Auth: JWT Bearer token; roles `ROLE_USER`, `ROLE_ADMIN`

---

## 7. Naming Conventions

| Item | Convention | Example |
|---|---|---|
| Java package | lowercase | `modules.travel` |
| Java class | PascalCase | `TravelServiceImpl` |
| DB table | snake_case, plural | `travel_packages` |
| DB column | snake_case | `created_at` |
| React component file | PascalCase | `TravelCard.jsx` |
| React hook | camelCase, `use` prefix | `useTravelList.js` |
| API route | kebab-case | `/event-booking` |
| Redux slice | camelCase | `travelSlice.js` |

---

## 8. Environments & Config

- `.env.development`, `.env.production` in frontend (never commit real secrets)
- `application-dev.yml`, `application-prod.yml` in backend, activated via `SPRING_PROFILES_ACTIVE`
- Secrets (DB password, JWT secret) via environment variables, not hardcoded

---

## 9. Checklist: Adding a New Module (e.g. "Yoga Retreats")

**Backend**
1. Create `modules/yoga/` with `entity`, `repository`, `dto/request`, `dto/response`, `service`, `service/impl`, `controller`, `mapper`
2. Add Flyway migration `V{n}__create_yoga_table.sql`
3. Add endpoints under `/api/v1/yoga`
4. Add admin endpoints under `/api/v1/admin/yoga`

**Frontend**
1. Create `modules/yoga/` with `pages`, `components`, `hooks`, `store`
2. Create `api/yoga.api.js`
3. Add routes in `AppRoutes.jsx`
4. Create `modules/admin/yogaManagement/` reusing `<DataTable>`/`<EntityForm>`
5. Add nav entry in `Navbar` / `AdminLayout` sidebar

That's it — same shape every time, so nothing feels bolted on.

---

## 10. Git & Commit Convention (optional but recommended)

- Branch: `feature/travel-module`, `fix/admin-login-bug`
- Commit: `feat(travel): add travel listing API`, `fix(admin): correct pagination bug`
