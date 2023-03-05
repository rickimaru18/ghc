# GHC - GitHub Client

A simple client where you can view all users and its details.

<img width="342" alt="image" src="https://user-images.githubusercontent.com/13028267/222944561-6a027b16-0b14-4958-88f8-711f2c2f66f2.png" />
<img width="342" alt="image" src="https://user-images.githubusercontent.com/13028267/222944633-759a0447-8869-4e1e-bfc2-c175f7382d33.png" />


## Setup

- IDE: Android Studio Electric Eel | 2022.1.1 Patch 2
- Architecture: CLEAN
- Branching strategy: [Trunk Based Development](https://trunkbaseddevelopment.com)
- Commit strategy: [Conventional Commits](https://www.conventionalcommits.org/en/v1.0.0-beta.2/)

### Optional setup
- To increase GitHub API access limit, add your `personal access token` in `GitHubDataSource.kt`, line `Bearer`.

## Things to improve

- Add unit testing
- Add widget testing
- Save state of previous screen when navigating
- Add flavors (prod, staging, dev)
- Add theme (colors, typography, etc.)
- Add crash reporting tool (e.g. Crashlytics)
- Add linters
- Display [payload](https://docs.github.com/en/webhooks-and-events/events/github-event-types) info in user details events list
