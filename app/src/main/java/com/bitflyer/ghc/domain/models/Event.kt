package com.bitflyer.ghc.domain.models

data class Event(
    val type: EventType,
    val repo: Repo,
    val org: Org?
)

enum class EventType {
    CommitCommentEvent,
    CreateEvent,
    DeleteEvent,
    ForkEvent,
    GollumEvent,
    IssueCommentEvent,
    IssuesEvent,
    MemberEvent,
    PublicEvent,
    PullRequestEvent,
    PullRequestReviewEvent,
    PullRequestReviewCommentEvent,
    PullRequestReviewThreadEvent,
    PushEvent,
    ReleaseEvent,
    SponsorshipEvent,
    WatchEvent,
    ;

    override fun toString(): String = when (this) {
        CommitCommentEvent -> "Commit Comment"
        CreateEvent -> "Branch/Tag Created"
        DeleteEvent -> "Branch/Tag Deleted"
        ForkEvent -> "Fork Repo"
        GollumEvent -> "Wiki Created/Updated"
        IssueCommentEvent -> "Issue/PR Comment"
        IssuesEvent -> "Issue Updated"
        MemberEvent -> "Member Added/Changed"
        PublicEvent -> "Make Repo Public"
        PullRequestEvent -> "PR Updated"
        PullRequestReviewEvent -> "PR Review"
        PullRequestReviewCommentEvent, PullRequestReviewThreadEvent -> "PR Review Comment"
        PushEvent -> "Branch/Tag Pushed"
        ReleaseEvent -> "Release"
        SponsorshipEvent -> "Sponsor"
        WatchEvent -> "Starred"
    }
}